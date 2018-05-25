DROP TABLE IF EXISTS workflow_file_task_global_file_purge_config;

--
-- Table structure for table form_action
--
CREATE TABLE workflow_file_task_global_file_purge_config (
	id_task INT NOT NULL,
	is_instant_purge SMALLINT DEFAULT 0 NOT NULL,
	nb_days_before_purge INT NULL DEFAULT NULL,
	PRIMARY KEY (id_task)
);
