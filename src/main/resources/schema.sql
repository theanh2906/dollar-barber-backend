DROP TABLE IF EXISTS ACCOUNTS;
DROP TABLE IF EXISTS DOCUMENTS;
CREATE TABLE IF NOT EXISTS ACCOUNTS (
    ID          NUMBER GENERATED BY DEFAULT AS IDENTITY,
    USERNAME    VARCHAR2(100) NOT NULL,
    PASSWORD    VARCHAR2(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS DOCUMENTS (
    ID          NUMBER GENERATED BY DEFAULT AS IDENTITY,
    TITLE       NVARCHAR2(50),
    CONTENT     NVARCHAR2(200)
);

CREATE TABLE IF NOT EXISTS FILES (
    ID              NUMBER GENERATED BY DEFAULT AS IDENTITY,
    NAME            NVARCHAR2(100) NOT NULL,
    BYTE            BLOB NOT NULL,
    UPLOAD_DATE     DATETIME
);