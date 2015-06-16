DROP TABLE IF EXISTS chat_sample.chat_log;
CREATE TABLE chat_sample.chat_log (
    id                          INT NOT NULL AUTO_INCREMENT,
    name                        VARCHAR(32) NOT NULL,
    chat                        TEXT NOT NULL,
    like_count                  INT NOT NULL DEFAULT 0,
    ip                          VARCHAR(64) NOT NULL,
    reg_date                    TIMESTAMP(0) NOT NULL,
    CONSTRAINT PRIMARY KEY (id)
);
