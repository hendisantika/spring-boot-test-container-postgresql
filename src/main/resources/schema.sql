CREATE TABLE if NOT EXISTS Post
(
    id
    INT
    NOT
    NULL,
    user_id
    INT
    NOT
    NULL,
    title
    VARCHAR
(
    250
) NOT NULL,
    body text NOT NULL,
    version INT,
    PRIMARY KEY
(
    id
)
    );
