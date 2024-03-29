<%@page import="internet.addBook"%> <%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Buchformular</title>
    <link
      href="http://fonts.googleapis.com/css?family=Bitter"
      rel="stylesheet"
      type="text/css"
    />
    <style type="text/css">
      .form-style-10 {
        width: 450px;
        padding: 30px;
        margin: 40px auto;
        background: #fff;
        border-radius: 10px;
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.13);
        -moz-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.13);
        -webkit-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.13);
      }
      .form-style-10 .inner-wrap {
        padding: 30px;
        background: #f8f8f8;
        border-radius: 6px;
        margin-bottom: 15px;
      }
      .form-style-10 h1 {
        background: #2a88ad;
        padding: 20px 30px 15px 30px;
        margin: -30px -30px 30px -30px;
        border-radius: 10px 10px 0 0;
        -webkit-border-radius: 10px 10px 0 0;
        -moz-border-radius: 10px 10px 0 0;
        color: #fff;
        text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.12);
        font: normal 30px "Bitter", serif;
        -moz-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
        -webkit-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
        box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
        border: 1px solid #257c9e;
      }
      .form-style-10 h1 > span {
        display: block;
        margin-top: 2px;
        font: 13px Arial, Helvetica, sans-serif;
      }
      .form-style-10 label {
        display: block;
        font: 13px Arial, Helvetica, sans-serif;
        color: #888;
        margin-bottom: 15px;
      }
      .form-style-10 input[type="text"],
      .form-style-10 input[type="date"],
      .form-style-10 input[type="datetime"],
      .form-style-10 input[type="email"],
      .form-style-10 input[type="number"],
      .form-style-10 input[type="search"],
      .form-style-10 input[type="time"],
      .form-style-10 input[type="url"],
      .form-style-10 input[type="password"],
      .form-style-10 textarea,
      .form-style-10 select {
        display: block;
        box-sizing: border-box;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        width: 100%;
        padding: 8px;
        border-radius: 6px;
        -webkit-border-radius: 6px;
        -moz-border-radius: 6px;
        border: 2px solid #fff;
        box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.33);
        -moz-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.33);
        -webkit-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.33);
      }

      .form-style-10 .section {
        font: normal 20px "Bitter", serif;
        color: #2a88ad;
        margin-bottom: 5px;
      }
      .form-style-10 .section span {
        background: #2a88ad;
        padding: 5px 10px 5px 10px;
        position: absolute;
        border-radius: 50%;
        -webkit-border-radius: 50%;
        -moz-border-radius: 50%;
        border: 4px solid #fff;
        font-size: 14px;
        margin-left: -45px;
        color: #fff;
        margin-top: -3px;
      }
      .form-style-10 input[type="button"],
      .form-style-10 input[type="submit"] {
        background: #2a88ad;
        padding: 8px 20px 8px 20px;
        border-radius: 5px;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        color: #fff;
        text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.12);
        font: normal 30px "Bitter", serif;
        -moz-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
        -webkit-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
        box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.17);
        border: 1px solid #257c9e;
        font-size: 15px;
      }
      .form-style-10 input[type="button"]:hover,
      .form-style-10 input[type="submit"]:hover {
        background: #2a6881;
        -moz-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.28);
        -webkit-box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.28);
        box-shadow: inset 0px 2px 2px 0px rgba(255, 255, 255, 0.28);
      }
      .form-style-10 .privacy-policy {
        float: right;
        width: 250px;
        font: 12px Arial, Helvetica, sans-serif;
        color: #4d4d4d;
        margin-top: 10px;
        text-align: right;
      }
      main {
        background: linear-gradient(#003471, #448ccb 30%);
        width: 80%;
        height: 500px;
        margin: 10px auto;
      }

      h1 {
        font-family: Rockwell, Georgia, "Times New Roman", Times, serif;
        font-size: 5em;
        line-height: 0.5em;
        margin: 0.3em 0.3em 1em 0.3em;
        color: #5c82d9;
        text-shadow: rgba(0, 0, 0, 0.5) -1px 0, rgba(0, 0, 0, 0.3) 0 -1px,
          rgba(255, 255, 255, 0.5) 2px 2px, rgba(0, 0, 0, 0.3) -1px -2px;
      }
      body {
        background-color: lightblue;
      }
    </style>
    <title>Add a book</title>
  </head>
  <body>
    <h1>Buchformular</h1>
    <div class="form-style-10">
      <h1>
        buch hinzüfügen<span
          >bitte die Daten in den feldern richtig eingeben
        </span>
      </h1>
      <form action="addBook" method="post">
        <div class="section"><span>1</span>title</div>
        <div class="inner-wrap">
          <label>title <input type="text" name="title" required /></label>
        </div>
        <div class="section"><span>2</span> ISBN</div>
        <div class="inner-wrap">
          <label> ISBN <input type="text" name="ISBN" required /></label>
        </div>

        <div class="section"><span>3</span>Preis</div>
        <div class="inner-wrap">
          <label>Preis <input type="number" name="Price" required step="0.01"/></label>
        </div>

        <div class="section"><span>4</span>Autor</div>
        <div class="inner-wrap">
          <label>Autor <input type="text" name="Author" required /></label>
        </div>

        <div class="section"><span>5</span>Describtion</div>
        <div class="inner-wrap">
          <label
            >Describtion <input type="text" name="Description" required
          /></label>
        </div>
        <div class="section"><span>6</span>Kategorie/n</div>
        <div class="inner-wrap">
    <%ArrayList<String> std =
        (ArrayList<String>)request.getAttribute("data");
    for(int i =0; i < std.size(); i++){%>
            <input type="checkbox" name=<%=i+1%>  />
            <%=std.get(i)%>
        <%}%>
        </div>

        <div class="button-section">
          <input type="submit" name="Sign Up" />
        </div>
      </form>
    </div>
  </body>
</html>
