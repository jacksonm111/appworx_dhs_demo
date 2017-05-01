<%@page contentType="text/html" pageEncoding="UTF-8"%>                                                    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"    
   "http://www.w3.org/TR/html4/loose.dtd">  
          
<html>                 <head>      
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AppWrx Demo 5/1/17</title>
        <!-- Latest compiled and minified CSS --> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css" integrity="sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX" crossorigin="anonymous">
        <style>   
        #textbox{
          margin-bottom: 10px;        
        }       
        #buttonRow{        
          margin-bottom: 10px;         
        }           
           
        </style>               
                       
    </head>         
    <body>            
    <!-- this is my sweet commit tester comment -->                           
    <nav class="navbar navbar-default navbar-fixed-top">  
        <div class="container"> 
          <div class="navbar-header"> 
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Appworks Demo Project</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right">
              <div class="form-group">
                <input type="text" placeholder="Email" class="form-control">
              </div>
              <div class="form-group">
                <input type="password" placeholder="Password" class="form-control">
              </div>
              <button type="submit" class="btn btn-success">Sign in</button>
            </form> 
          </div><!--/.navbar-collapse -->
        </div>  
      </nav>                   
 
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron"> 
      <div class="container"> 
        <h1>Welcome to the Demo!</h1>     
        <h2>hello</h2>  
        <p>Check out the sample calculator below.</p>
      </div>
    </div>
  
    <div class="container"> 
        <!-- Example row of columns -->
          <div class="col-lg-4 col-lg-offset-4">
            <form Name="calc">
              <table>
              <tr>
              <td colspan=4><input type="text" id="textbox" class="form-control" Name="display" readonly></td>
              </tr>
              <tr>
              <td><input id="buttonRow" type="button" class="btn btn-default btn-lg" value="0" OnClick="calc.display.value+='0'"></td>
              <td><input id="buttonRow" type="button" class="btn btn-default btn-lg" value="1" OnClick="calc.display.value+='1'"></td>
              <td><input id="buttonRow" type="button" class="btn btn-default btn-lg" value="2" OnClick="calc.display.value+='2'"></td>
              <td><input id="buttonRow" type="button" class="btn btn-danger btn-lg" value="+" OnClick="calc.display.value+='+'"></td>
              </tr>
              <tr>
              <td><input id="buttonRow" type="button" class="btn btn-default btn-lg" value="3" OnClick="calc.display.value+='3'"></td>
              <td><input id="buttonRow" type="button" class="btn btn-default btn-lg" value="4" OnClick="calc.display.value+='4'"></td>
              <td><input id="buttonRow" type="button" class="btn btn-default btn-lg" value="5" OnClick="calc.display.value+='5'"></td>
              <td><input id="buttonRow" type="button" class="btn btn-danger btn-lg" value="-" OnClick="calc.display.value+='-'"></td>
              </tr>
              <tr>
              <td><input id="buttonRow" type="button" class="btn btn-default btn-lg" value="6" OnClick="calc.display.value+='6'"></td>
              <td><input id="buttonRow" type="button" class="btn btn-default btn-lg" value="7" OnClick="calc.display.value+='7'"></td>
              <td><input id="buttonRow" type="button" class="btn btn-default btn-lg" value="8" OnClick="calc.display.value+='8'"></td>
              <td><input id="buttonRow" type="button" class="btn btn-danger btn-lg" value="x" OnClick="calc.display.value+='*'"></td>
              </tr>
              <tr>
              <td><input id="buttonRow" type="button" class="btn btn-default btn-lg" value="9" OnClick="calc.display.value+='9'"></td>
              <td><input id="buttonRow" type="button" class="btn btn-default btn-lg" value="C" OnClick="calc.display.value=''"></td>
              <td><input id="buttonRow" type="button" class="btn btn-warning btn-lg" value="=" OnClick="calc.display.value=eval(calc.display.value)"></td>
              <td><input id="buttonRow" type="button" class="btn btn-danger btn-lg" value="/" OnClick="calc.display.value+='/'"></td>
              </tr>
              </table>
            </form>
         </div>
      </div>   

      <hr>

        <!--JS down here for faster load  -->
        <!-- Latest compiled and minified JavaScript venkatTest1-3 1/11/2016-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
    </body>
</html>



