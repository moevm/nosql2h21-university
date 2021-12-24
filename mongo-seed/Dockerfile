FROM mongo

COPY initEmployees.json /initEmployees.json
COPY initEnrollees.json /initEnrollees.json
COPY initUniversities.json /initUniversities.json
CMD mongoimport --host mongodb --port 27017 --username rootuser --password rootpass --authenticationDatabase admin --db universitiesnapplicants --mode upsert --collection employees --type json --file /initEmployees.json --jsonArray && mongoimport --host mongodb --port 27017 --username rootuser --password rootpass --authenticationDatabase admin --db universitiesnapplicants --mode upsert --collection enrollees --type json --file /initEnrollees.json --jsonArray && mongoimport --host mongodb --port 27017 --username rootuser --password rootpass --authenticationDatabase admin --db universitiesnapplicants --mode upsert --collection universities --type json --file /initUniversities.json --jsonArray
