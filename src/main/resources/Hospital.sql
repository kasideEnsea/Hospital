CREATE DATABASE hospital ENCODING 'UTF-8';

CREATE TABLE department (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  patient_count INTEGER DEFAULT 0
);

CREATE TYPE GENDER AS ENUM ('male', 'female');

CREATE TABLE patient (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  birthday TIMESTAMP NOT NULL,
  gender GENDER NOT NULL,
  department_id INTEGER,
  FOREIGN KEY (department_id) REFERENCES department (id)
);

INSERT INTO department (name, patient_count)
VALUES ('Traumatology', 2);
INSERT INTO department (name, patient_count)
VALUES ('Surgical', 2);
INSERT INTO department (name, patient_count)
VALUES ('Emergency', 2);
INSERT INTO department (name, patient_count)
VALUES ('Сlinical', 2);

INSERT INTO patient (name, birthday, gender, department_id)
VALUES ('Pavlova Victoria Konstantinovna', '27/03/2000', 'female', 1);
INSERT INTO patient (name, birthday, gender, department_id)
VALUES ('Markov Kyrill Petrovich', '25/02/2001', 'male', 1);
INSERT INTO patient (name, birthday, gender, department_id)
VALUES ('Denisenko Angela Valerievna', '04/12/1999', 'female', 2);
INSERT INTO patient (name, birthday, gender, department_id)
VALUES ('Sagitov Ivan Sergeevich', '15/05/1995', 'male', 2);
INSERT INTO patient (name, birthday, gender, department_id)
VALUES ('Ivanov Ivan Ivanovich', '01/01/2002', 'male', 3);
INSERT INTO patient (name, birthday, gender, department_id)
VALUES ('Makarov Igor Petrovich', '13/11/1997', 'male', 3);
INSERT INTO patient (name, birthday, gender, department_id)
VALUES ('Petrova Nadezda Olegovna', '23/06/2001', 'female', 4);
INSERT INTO patient (name, birthday, gender, department_id)
VALUES ('Nesterenko Pavel Sergeevich', '25/02/1995', 'male', 4);
INSERT INTO patient (name, birthday, gender, department_id)
VALUES ('Petrov Petr Petrovich', '25/02/2001', 'male', null);
