drop table if exists Omim;
create table Omim(     mimNumber text,    
                      preferredTitle text,
                     Disease text,
          Symptom text
                    );

LOAD DATA LOCAL INFILE 'OmimData.csv' INTO TABLE Omim FIELDS TERMINATED BY '\t';


