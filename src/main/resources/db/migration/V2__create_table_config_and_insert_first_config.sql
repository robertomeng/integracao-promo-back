CREATE TABLE public.config (
	tempo_scan int4 NOT NULL DEFAULT 300000,
	id serial NOT NULL,
	CONSTRAINT config_pk PRIMARY KEY (id)
);

-- 5 minutos
INSERT INTO public.config
(tempo_scan)
VALUES(300000);