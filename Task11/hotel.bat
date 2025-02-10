@echo off
set PGHOST=localhost
set PGPORT=5432
set PGUSER=postgres
set PGPASSWORD=12345
set DATABASE_NAME=hotel_management
psql -U %PGUSER% -h %PGHOST% -p %PGPORT% -c "CREATE DATABASE %Hotel%;"
psql -U %PGUSER% -h %PGHOST% -p %PGPORT% -d %DATABASE_NAME% -f hotel_database_ddl.sql
psql -U %PGUSER% -h %PGHOST% -p %PGPORT% -d %DATABASE_NAME% -f hotel_database_dml.sql
echo Database setup complete!
