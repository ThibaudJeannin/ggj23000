# ggj23000

launch app    
`gradle run`  
redefine env `PORT` if necessary

launch postgres db  
`docker run -it --rm --name test-postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres`