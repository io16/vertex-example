CREATE TABLE d13_activity_v3
(
    from_local timestamp NOT NULL,
    to_local timestamp NOT NULL,
    user_id int NOT NULL,
    project_id int NOT NULL,
    type_id char NOT NULL,
    time_zone text  NOT NULL,
    category_id int NOT NULL,
    process_id int,
    document_url text,
    duration_ms int
);

CREATE INDEX d13_activity_v3_from_utc_index ON public.d13_activity_v3 (from_local);
CREATE INDEX d13_activity_v3_to_utc_index ON public.d13_activity_v3 (to_local);
CREATE INDEX d13_activity_v3user_id_index ON public.d13_activity_v3 (user_id);
CREATE INDEX d13_activity_v3_project_id_index ON public.d13_activity_v3 (project_id);
CREATE INDEX d13_activity_v3_type_id_index ON public.d13_activity_v3 (type_id);
CREATE INDEX d13_activity_v3_category_id_index ON public.d13_activity_v3 (category_id);
CREATE INDEX d13_activity_v3process_id_index ON public.d13_activity_v3 (process_id);

