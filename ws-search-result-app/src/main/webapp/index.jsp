<!DOCTYPE html>
<html>
<head>
<style>
header {
    background-color:blue;
    color:white;
    text-align:center;
    padding:5px;
}
nav {
    line-height:25px;
    background-color:#eeeeee;
    height:500px;
    width:300px;
    float:left;
    padding:5px;
}
section {
    width:650px;
    float:left;
    padding:1px;
}
footer {
    background-color:blue;
    color:white;
    clear:both;
    text-align:center;
    padding:5px;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    vertical-align: bottom;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color:blue;
    color: white;

</style>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
</head>
<body>

<header>
<h1>Hotel Search</h1>
 <form action="/ws/search/" id="searchForm">
            <input id="query" type="text" name="query" placeholder="Search..." />
            <input type="submit" value="Search" />
        </form>
</header>

<nav id="cities">

</nav>

<section >
<div id="hotels" style="overflow-x:auto;">
</div>
</section>

<footer>
Copyright © Hotels.com
</footer>
 <script>

var QueryString = function () {
  // This function is anonymous, is executed immediately and
  // the return value is assigned to QueryString!
  var query_string = {};
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i=0;i<vars.length;i++) {
    var pair = vars[i].split("=");
        // If first entry with this name
    if (typeof query_string[pair[0]] === "undefined") {
      query_string[pair[0]] = decodeURIComponent(pair[1]);
        // If second entry with this name
    } else if (typeof query_string[pair[0]] === "string") {
      var arr = [ query_string[pair[0]],decodeURIComponent(pair[1]) ];
      query_string[pair[0]] = arr;
        // If third or later entry with this name
    } else {
      query_string[pair[0]].push(decodeURIComponent(pair[1]));
    }
  }
  return query_string;
}();

var getQuery = function(){
     var $form = $("#searchForm"),
     term = $form.find('input[name="query"]').val();
     return term;
}

var getURL = function(){
     var $form = $("#searchForm"),
     url = $form.attr('action');
     return url;
}

var clean = function() {
    $("#hotels").empty();
    $("#cities").empty();
}

var search = function(term, cityName) {

     /* Send the data using post */
     var posting = $.get(getURL(), {
         query: term,
         city: cityName
     });

     posting.done(function(data) {

        clean();

         if(data.hotels.length != 0){
             var tab = "<table></table>";
             var th = "<th>Name</th><th>City</th><th>Country</th><th>Stars</th><th>Booking</th><th>Score</th>"
             var td = "<td></td>";
             var tr = "<tr></tr>"
             var table = $(tab)
             var head = $(tr).append(th);
             table.append(head);
             for (i = 0; i < data.hotels.length; i++) {
                 td = "<td></td>";
                 tr = "<tr></tr>"
                 var name = $(td).text(data.hotels[i].name);
                 var city = $(td).text(data.hotels[i].city);
                 var country = $(td).text(data.hotels[i].country);
                 var score = $(td).text(data.hotels[i].score);
                 var stars = $(td).text(data.hotels[i].stars);
                 var bookings = $(td).text(data.hotels[i].bookings);
                 var line = $(tr).append(name,city,country, stars, bookings, score);
                 table.append(line);
             }
             $("#hotels").append("Result for the term <b>" + term  + "</b>. Found " + data.numFound + " hotel(s)");
             $("#hotels").append(table);
         } else{
             $("#hotels").append("Not found result for the term <em>" + term  + "<em>");
         }

         if(data.cities.length > 1){
             $("#cities").append("Cities<br>");
             for (i = 0; i < data.cities.length; i++) {
                 $("#cities").append("<a id='linkCity' href='?query="+term+"&city="+ data.cities[i].name + "'>"+data.cities[i].name+" ("+data.cities[i].count+") </a><br>");
             }
         }
     });

};



var submit = function(event) {
    event.preventDefault();
    term = getQuery();
    if(term == undefined || term.trim() == ""){
        clean();
        return
    }
    search(term, "");
}

var doSearch = function() {
    term = getQuery();
    if(term == undefined || term.trim() == ""){
        clean();
        return
    }
    search(term, "");
}


if(QueryString != undefined && QueryString.query != undefined && QueryString.query != ""){
    $("#query").val(QueryString.query);
    if(QueryString.city != undefined && QueryString.city != null){
        search(QueryString.query, QueryString.city);
    }else{
        search(QueryString.query, "");
    }

}

$("#searchForm").submit(submit);
$("#query").keyup(doSearch);
$("#linkCity").click(doSearch);
</script>
</body>
</html>