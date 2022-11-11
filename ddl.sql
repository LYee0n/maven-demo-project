-- public.account definition

-- Drop table

-- DROP TABLE public.account;

CREATE TABLE public.account (
	id varchar NOT NULL DEFAULT gen_random_uuid(),
	"name" varchar NOT NULL,
	"password" varchar NOT NULL,
	email varchar NOT NULL,
	create_time timestamp NOT NULL DEFAULT now(),
	webhook_endpoint varchar NULL,
	status varchar NOT NULL DEFAULT 'Y'::character varying,
	CONSTRAINT account_pk PRIMARY KEY (id),
	CONSTRAINT account_un UNIQUE (name)
);


-- public.ftp_server definition

-- Drop table

-- DROP TABLE public.ftp_server;

CREATE TABLE public.ftp_server (
	id varchar NOT NULL DEFAULT gen_random_uuid(),
	"name" varchar NOT NULL,
	ip varchar NOT NULL,
	fqdn varchar NOT NULL,
	create_time timestamp NOT NULL DEFAULT now(),
	is_self_host varchar NOT NULL DEFAULT 'Y'::character varying,
	CONSTRAINT ftp_server_pk PRIMARY KEY (id)
);


-- public.ftp_server_account definition

-- Drop table

-- DROP TABLE public.ftp_server_account;

CREATE TABLE public.ftp_server_account (
	id varchar NOT NULL,
	account_id varchar NOT NULL,
	ftp_server_id varchar NOT NULL,
	account varchar NOT NULL,
	"password" varchar NOT NULL,
	status varchar NOT NULL,
	create_time timestamp NOT NULL DEFAULT now(),
	CONSTRAINT ftp_server_account_fk FOREIGN KEY (ftp_server_id) REFERENCES public.ftp_server(id),
	CONSTRAINT ftp_server_account_fk_1 FOREIGN KEY (account_id) REFERENCES public.account(id)
);


-- public.station definition

-- Drop table

-- DROP TABLE public.station;

CREATE TABLE public.station (
	id varchar NOT NULL DEFAULT gen_random_uuid(),
	"name" varchar NOT NULL,
	task_queue varchar NOT NULL,
	notify_queue varchar NOT NULL,
	status varchar NOT NULL,
	ftp_server_id varchar NOT NULL,
	create_time timestamp NOT NULL DEFAULT now(),
	CONSTRAINT station_fk FOREIGN KEY (ftp_server_id) REFERENCES public.ftp_server(id)
);


-- public.task definition

-- Drop table

-- DROP TABLE public.task;

CREATE TABLE public.task (
	id varchar NOT NULL DEFAULT gen_random_uuid(),
	origin_ftp_server_id varchar NOT NULL,
	dest_ftp_server_id varchar NOT NULL,
	file_path varchar NOT NULL,
	status varchar NOT NULL,
	create_time timestamp NOT NULL DEFAULT now(),
	create_account_id varchar NOT NULL,
	finish_time timestamp NULL,
	dest_account_id varchar NOT NULL,
	CONSTRAINT task_pk PRIMARY KEY (id),
	CONSTRAINT task_account_fk FOREIGN KEY (create_account_id) REFERENCES public.account(id),
	CONSTRAINT task_dest_account_fk FOREIGN KEY (dest_account_id) REFERENCES public.account(id),
	CONSTRAINT task_dest_ftp_server_fk FOREIGN KEY (dest_ftp_server_id) REFERENCES public.ftp_server(id),
	CONSTRAINT task_origin_ftp_server_fk FOREIGN KEY (origin_ftp_server_id) REFERENCES public.ftp_server(id)
);