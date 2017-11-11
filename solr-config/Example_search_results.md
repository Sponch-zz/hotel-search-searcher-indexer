In this file, I'll show some results that I've resolved in the configuration of Solr:

Search for: ***China***: The term china inside the name field is more important than inside the country field.

      http://localhost:8983/solr/hotels/select?indent=on&wt=json&q=china

```json
{
        "name":"Hotel China",
        "city":"Hong Kong",
        "country":"Hong Kong",
        "stars":3,
        "bookings":10,
        "score":33.598816},
      {
        "name":"ParadiseInn Peace Beijing Hotel",
        "city":"Beijing",
        "country":"China",
        "stars":4,
        "bookings":100,
        "score":26.969707},
      {
        "name":"ParadiseInn Shanghai Hotel",
        "city":"Shanghai",
        "country":"China",
        "stars":3,
        "bookings":10,
        "score":11.9697075}
```

Search for: ***fl***: With adjusts in the synonyms config, now the hotels from Miami will return in the results.
Return Florianopolis city because of NGram filter.
 
       http://localhost:8983/solr/hotels/select?indent=on&wt=json&q=fl

```json
{
        "name":"Made4You Hotel",
        "city":"Miami (FL)",
        "country":"United States",
        "stars":2,
        "bookings":10,
        "score":8.982807},
      {
        "name":"Made For You Hotel",
        "city":"Miami",
        "country":"United States",
        "stars":2,
        "bookings":10,
        "score":7.5991836},
      {
        "name":"Hotel ParadiseInn São Sebastião",
        "city":"Florianopolis",
        "country":"Brazil",
        "stars":3,
        "bookings":10,
        "score":7.2082405}
```

Search for: ***Made4You***: The search will return Made For You too, with exact match return in the first result. 


        http://localhost:8983/solr/hotels/select?indent=on&wt=json&q=made4you

```json
{
        "name":"Made4You Hotel",
        "city":"Miami (FL)",
        "country":"United States",
        "stars":2,
        "bookings":10,
        "score":41.271233},
      {
        "name":"Made For You Hotel",
        "city":"Miami",
        "country":"United States",
        "stars":2,
        "bookings":10,
        "score":21.201656}
```

Search for: ***made for you***: The search will return the Made4You too, considerating the exact match

```json
{
        "name":"Made For You Hotel",
        "city":"Miami",
        "country":"United States",
        "stars":2,
        "bookings":10,
        "score":68.95948},
      {
        "name":"Made4You Hotel",
        "city":"Miami (FL)",
        "country":"United States",
        "stars":2,
        "bookings":10,
        "score":53.125134}
```

Search for: ***Blue Sky***: Search will return "BlueSky Hostel" too, using the WordDelimiterFilterFactory filter

```json
{
        "name":"The Blue Sky Resort",
        "city":"London",
        "country":"United Kingdom",
        "stars":5,
        "bookings":10,
        "score":52.797348},
      {
        "name":"BlueSky Hostel",
        "city":"Paris",
        "country":"France",
        "stars":2,
        "bookings":10,
        "score":46.4364}
```

Search for: ***BlueSky***: The result will return The Blue Sky Resort too. It was necessary 

```json
{
        "name":"BlueSky Hostel",
        "city":"Paris",
        "country":"France",
        "stars":2,
        "bookings":10,
        "score":28.729866},
      {
        "name":"The Blue Sky Resort",
        "city":"London",
        "country":"United Kingdom",
        "stars":5,
        "bookings":10,
        "score":19.256464}
```

Search for: ***Resort & Spa***

```json
      {
        "name":"ParadiseInn Resort & Spa",
        "city":"Phuket",
        "country":"Thailand",
        "stars":5,
        "bookings":50,
        "score":60.26315},
      {
        "name":"Huahin Resort and Spa",
        "city":"Hua Hin/Cha-am",
        "country":"Thailand",
        "stars":4,
        "bookings":10,
        "score":46.778603}
```

Search for: ***Resort and Spa***

```json
      {
        "name":"Huahin Resort and Spa",
        "city":"Hua Hin/Cha-am",
        "country":"Thailand",
        "stars":4,
        "bookings":10,
        "score":61.676224},
      {
        "name":"ParadiseInn Resort & Spa",
        "city":"Phuket",
        "country":"Thailand",
        "stars":5,
        "bookings":50,
        "score":60.26315}  
```

Search for: ***king's or king' or king or kings***

```json
{
        "name":"ParadiseInn King's Hotel",
        "city":"Singapore",
        "country":"Singapore",
        "stars":3,
        "bookings":10,
        "score":33.598816},
      {
        "name":"The Blue Sky Resort",
        "city":"London",
        "country":"United Kingdom",
        "stars":5,
        "bookings":10,
        "score":13.2082405},
      {
        "name":"Big Bang Hotel (BBH)",
        "city":"London",
        "country":"United Kingdom",
        "stars":2,
        "bookings":10,
        "score":4.2082405}
```

Search for: ***"São Sebastião" or "Sao Sebastiao"***

```json
{
        "name":"Hotel ParadiseInn São Sebastião",
        "city":"Florianopolis",
        "country":"Brazil",
        "stars":3,
        "bookings":10,
        "score":57.524685},
      {
        "name":"Pousada das Orquídeas",
        "city":"Sao Sebastiao",
        "country":"Brazil",
        "stars":2,
        "bookings":10,
        "score":10.795927}
```

Search for: ***The Hotel at the Sun***

```json
{
        "name":"The Hotel at the Sun",
        "city":"Los Angeles (CA)",
        "country":"United States",
        "stars":4,
        "bookings":10,
        "score":99.169876}]
  }
```

Search for: ***check-in or checkin***

```json
{
        "name":"Check-in Hotel and Villa",
        "city":"Bali",
        "country":"Indonesia",
        "stars":4,
        "bookings":10,
        "score":38.39057}
```

Search for: ***Hua Hin/Cha-am***

```json
{
        "name":"Huahin Resort and Spa",
        "city":"Hua Hin/Cha-am",
        "country":"Thailand",
        "stars":4,
        "bookings":10,
        "score":19.891275}]
  }
```

Search for: ***island or islands***

```json
      {
        "name":"Resort ParadiseInn Maldives",
        "city":"Maldives Islands",
        "country":"Maldives",
        "stars":5,
        "bookings":10,
        "score":16.521856},
      {
        "name":"ParadiseInn Hotel",
        "city":"Batam Island",
        "country":"Indoneia",
        "stars":1,
        "bookings":0,
        "score":-7.478143}
```

Search for: ***pousada (portuguese) or inn***

```json
{
        "name":"ParadiseInn Resort & Spa",
        "city":"Phuket",
        "country":"Thailand",
        "stars":5,
        "bookings":50,
        "score":27.140533},
      {
        "name":"Pousada das Orquídeas",
        "city":"Sao Sebastiao",
        "country":"Brazil",
        "stars":2,
        "bookings":10,
        "score":26.129385}
    .......
```

