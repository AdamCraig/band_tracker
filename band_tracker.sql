--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bands; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE bands (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE bands OWNER TO "Guest";

--
-- Name: bands_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE bands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_id_seq OWNER TO "Guest";

--
-- Name: bands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE bands_id_seq OWNED BY bands.id;


--
-- Name: bands_venues; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE bands_venues (
    id integer NOT NULL,
    band_id integer,
    venue_id integer
);


ALTER TABLE bands_venues OWNER TO "Guest";

--
-- Name: bands_venues_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE bands_venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_venues_id_seq OWNER TO "Guest";

--
-- Name: bands_venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE bands_venues_id_seq OWNED BY bands_venues.id;


--
-- Name: venues; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE venues (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE venues OWNER TO "Guest";

--
-- Name: venues_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE venues_id_seq OWNER TO "Guest";

--
-- Name: venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE venues_id_seq OWNED BY venues.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY bands ALTER COLUMN id SET DEFAULT nextval('bands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY bands_venues ALTER COLUMN id SET DEFAULT nextval('bands_venues_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY venues ALTER COLUMN id SET DEFAULT nextval('venues_id_seq'::regclass);


--
-- Data for Name: bands; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY bands (id, name) FROM stdin;
14	Metallica
15	Megadeth
16	Coheed And Cambria
17	The Dear Hunter
18	The Beatles
19	Boston
20	The 1975
21	Brand New
22	Thrice
\.


--
-- Name: bands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('bands_id_seq', 22, true);


--
-- Data for Name: bands_venues; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY bands_venues (id, band_id, venue_id) FROM stdin;
14	14	11
15	14	12
16	15	13
17	16	14
18	16	15
19	17	16
20	16	17
21	18	18
22	19	19
23	20	20
24	21	21
25	22	14
\.


--
-- Name: bands_venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('bands_venues_id_seq', 25, true);


--
-- Data for Name: venues; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY venues (id, name) FROM stdin;
11	TD Garden
12	Madison Square Garden
13	Tsongas Arena
14	House of Blues Boston
15	The Palladium
16	The Sinclair
17	Radio City Music Hall
18	Every Venue Imaginable
19	Every Classic Rock Venue
20	Arlene Schnitzer Concert Hall
21	Moda Center
22	Staples Center
\.


--
-- Name: venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('venues_id_seq', 22, true);


--
-- Name: bands_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY bands
    ADD CONSTRAINT bands_pkey PRIMARY KEY (id);


--
-- Name: bands_venues_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY bands_venues
    ADD CONSTRAINT bands_venues_pkey PRIMARY KEY (id);


--
-- Name: venues_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY venues
    ADD CONSTRAINT venues_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

