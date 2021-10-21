--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_table_access_method = heap;

--
-- Name: tb_comments; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tb_comments (
    comment_id bigint NOT NULL,
    comment_message character varying(255),
    comment_author character varying(255),
    post_id bigint,
    user_id bigint
);


--
-- Name: tb_comments_comment_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.tb_comments ALTER COLUMN comment_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.tb_comments_comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: tb_posts; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tb_posts (
    id bigint NOT NULL,
    post_description character varying(255),
    user_name character varying(255),
    user_id bigint
);


--
-- Name: tb_posts_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.tb_posts ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.tb_posts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: tb_users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tb_users (
    id bigint NOT NULL,
    name character varying(255)
);


--
-- Name: tb_users_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.tb_users ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.tb_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: tb_comments tb_comments_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_comments
    ADD CONSTRAINT tb_comments_pkey PRIMARY KEY (comment_id);


--
-- Name: tb_posts tb_posts_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_posts
    ADD CONSTRAINT tb_posts_pkey PRIMARY KEY (id);


--
-- Name: tb_users tb_users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_users
    ADD CONSTRAINT tb_users_pkey PRIMARY KEY (id);


--
-- Name: tb_comments fkfj0selagyfap7rpr22j48wmod; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_comments
    ADD CONSTRAINT fkfj0selagyfap7rpr22j48wmod FOREIGN KEY (post_id) REFERENCES public.tb_posts(id);


--
-- Name: tb_posts fkfwlqo0sdgi9tus13jvkgfre8x; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_posts
    ADD CONSTRAINT fkfwlqo0sdgi9tus13jvkgfre8x FOREIGN KEY (user_id) REFERENCES public.tb_users(id);


--
-- Name: tb_comments fktre89kkljhdw1bm7n127mxi8n; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_comments
    ADD CONSTRAINT fktre89kkljhdw1bm7n127mxi8n FOREIGN KEY (user_id) REFERENCES public.tb_users(id);


--
-- PostgreSQL database dump complete
--
