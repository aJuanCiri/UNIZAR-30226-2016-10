-- Trigger encargado de la gestión de la valoración media de cada una de las películas
-- de la colección pública.

CREATE OR REPLACE TRIGGER valoracion_media
BEFORE INSERT ON Vista
FOR EACH ROW
DECLARE
	cuenta NUMBER(6);
	valoracion_pelicula NUMBER(10);
BEGIN
	SELECT COUNT(*) INTO cuenta FROM Vista WHERE (pelicula=:new.pelicula);
	SELECT puntuacion INTO valoracion_pelicula FROM Pelicula WHERE (id=:new.pelicula); 
	UPDATE Pelicula SET valoracion=((valoracion_pelicula+:new.valoracion)/(cuenta + 1)) WHERE (id=:new.pelicula);
	UPDATE Pelicula SET puntuacion=(valoracion_pelicula+:new.valoracion) WHERE (id=:new.pelicula);
END;
/