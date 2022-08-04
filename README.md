```sql
select *
from syo_tag

INSERT INTO public.syo_tag
(id_tag, ds_tag)
VALUES(1, 'Tag 1');
INSERT INTO public.syo_tag
(id_tag, ds_tag)
VALUES(2, 'Tag 2');
INSERT INTO public.syo_tag
(id_tag, ds_tag)
VALUES(3, 'Tag 3');
INSERT INTO public.syo_tag
(id_tag, ds_tag)
VALUES(4, 'Tag 4');

```

GET http://localhost:8082/tag/list

GET http://localhost:8082/logging/{package name}     
   - Ex: http://localhost:8082/logging/org.acme
   - Ex: http://localhost:8082/logging/org.hibernate

GET http://localhost:8082/logging/org.acme/
   - Ex: http://localhost:8082/logging/org.acme?level=INFO
