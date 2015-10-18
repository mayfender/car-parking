select count(id) as num, year(in_date_time) as year
from vehicle_parking
group by year(in_date_time)
order by in_date_time asc