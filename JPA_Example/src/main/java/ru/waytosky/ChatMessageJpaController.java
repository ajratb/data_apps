/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ru.waytosky.exceptions.NonexistentEntityException;

/**
 *
 * @author Ayrat
 */
public class ChatMessageJpaController implements Serializable {

    public ChatMessageJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ChatMessage chatMessage) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(chatMessage);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ChatMessage chatMessage) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            chatMessage = em.merge(chatMessage);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = chatMessage.getId();
                if (findChatMessage(id) == null) {
                    throw new NonexistentEntityException("The chatMessage with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ChatMessage chatMessage;
            try {
                chatMessage = em.getReference(ChatMessage.class, id);
                chatMessage.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chatMessage with id " + id + " no longer exists.", enfe);
            }
            em.remove(chatMessage);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ChatMessage> findChatMessageEntities() {
        return findChatMessageEntities(true, -1, -1);
    }

    public List<ChatMessage> findChatMessageEntities(int maxResults, int firstResult) {
        return findChatMessageEntities(false, maxResults, firstResult);
    }

    private List<ChatMessage> findChatMessageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ChatMessage.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ChatMessage findChatMessage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ChatMessage.class, id);
        } finally {
            em.close();
        }
    }

    public int getChatMessageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ChatMessage> rt = cq.from(ChatMessage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
