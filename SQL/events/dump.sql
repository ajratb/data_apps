--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: event; Type: TABLE; Schema: public; Owner: dev
--

CREATE TABLE event (
    id integer NOT NULL,
    ev_name character varying(255)
);


ALTER TABLE event OWNER TO dev;

--
-- Name: event_id_seq; Type: SEQUENCE; Schema: public; Owner: dev
--

CREATE SEQUENCE event_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE event_id_seq OWNER TO dev;

--
-- Name: event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dev
--

ALTER SEQUENCE event_id_seq OWNED BY event.id;


--
-- Name: event_tag; Type: TABLE; Schema: public; Owner: dev
--

CREATE TABLE event_tag (
    event_id integer,
    tag_id integer
);


ALTER TABLE event_tag OWNER TO dev;

--
-- Name: tag; Type: TABLE; Schema: public; Owner: dev
--

CREATE TABLE tag (
    id integer NOT NULL,
    tag_name character varying(255)
);


ALTER TABLE tag OWNER TO dev;

--
-- Name: tag_id_seq; Type: SEQUENCE; Schema: public; Owner: dev
--

CREATE SEQUENCE tag_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tag_id_seq OWNER TO dev;

--
-- Name: tag_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dev
--

ALTER SEQUENCE tag_id_seq OWNED BY tag.id;


--
-- Name: event id; Type: DEFAULT; Schema: public; Owner: dev
--

ALTER TABLE ONLY event ALTER COLUMN id SET DEFAULT nextval('event_id_seq'::regclass);


--
-- Name: tag id; Type: DEFAULT; Schema: public; Owner: dev
--

ALTER TABLE ONLY tag ALTER COLUMN id SET DEFAULT nextval('tag_id_seq'::regclass);


--
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: dev
--

COPY event (id, ev_name) FROM stdin;
1	meeting
2	birthday
3	some_trouble
4	nice talk
5	
6	routine
\.


--
-- Data for Name: event_tag; Type: TABLE DATA; Schema: public; Owner: dev
--

COPY event_tag (event_id, tag_id) FROM stdin;
1	1
2	4
3	3
1	3
1	2
4	4
3	6
\.


--
-- Data for Name: tag; Type: TABLE DATA; Schema: public; Owner: dev
--

COPY tag (id, tag_name) FROM stdin;
1	with friends
2	\N
3	with girl
4	myself
5	buy_a_gift
6	better_to_miss
\.


--
-- Name: event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dev
--

SELECT pg_catalog.setval('event_id_seq', 6, true);


--
-- Name: tag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dev
--

SELECT pg_catalog.setval('tag_id_seq', 6, true);


--
-- Name: event event_pkey; Type: CONSTRAINT; Schema: public; Owner: dev
--

ALTER TABLE ONLY event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);


--
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: public; Owner: dev
--

ALTER TABLE ONLY tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- Name: event_tag event_tag_event_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dev
--

ALTER TABLE ONLY event_tag
    ADD CONSTRAINT event_tag_event_id_fkey FOREIGN KEY (event_id) REFERENCES event(id);


--
-- Name: event_tag event_tag_tag_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dev
--

ALTER TABLE ONLY event_tag
    ADD CONSTRAINT event_tag_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES tag(id);


--
-- PostgreSQL database dump complete
--

