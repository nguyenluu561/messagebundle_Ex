CREATE OR REPLACE function getEmployeesByName(name varchar) RETURNS refcursor AS
$BODY$
	DECLARE
		employees refcursor;
		BEGIN
			OPEN employees FOR SELECT * from employee where firstname = name
			RETURN employess;
		END 
$BODY$
LANGUAGE plpgsql