<%--
  Created by IntelliJ IDEA.
  User: justinkang
  Date: 6/28/16
  Time: 10:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lions Lunch Form</title>

    <link rel="stylesheet" type="text/css" href="WeeklyForm.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

    <%--<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

</head>

<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="LunchForm.jsp">Lion's Lunches</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="index.jsp">Home</a></li>
            <li class="active"><a href="LunchForm.jsp">Sign-Up Form</a></li>
        </ul>
    </div>
</nav>

<div id="content">
<%--<div id="header">--%>
    <h1>Welcome to Lions Lunches Form</h1>
<%--</div>--%>

<form class="form-horizontal" action = "WeeklyForm" method = "post" id = "formID">
    <table align="center">

        <tr>
            <td class="lbl"><label class="field" for = "name">First and Last Name: </label></td>
            <td><input type = "text" pattern="[a-zA-Z\s]+" name = "name" id = "name" placeholder = "Justin Kang" required></td>
        </tr>

        <tr>
            <td><label class="field" for = "eid">UT EID: </label></td>
            <td><input type = "text" pattern="[a-zA-Z0-9]+" name = "eid" id = "eid" placeholder = "jk36542" maxlength="10" required></td>
        </tr>

        <tr>
            <td><label class="field" for = "phoneNumber">Phone Number:</label></td>
            <td><input type = "tel" pattern="(?:\(\d{3}\)|\d{3})[- ]?\d{3}[- ]?\d{4}" name = "phoneNumber" id = "phoneNumber" placeholder = "4692366449" title="Please enter your phone number in the form 4692366449 with no spaces or symbols." required></td>
        </tr>

        <tr>
            <td><label class="field" for="email">Email Address: </label></td>
            <td><input type = "email" name = "email" id="email" placeholder="justin.kang@utexas.edu" required></td>
        </tr>

        <tr>
            <td><label class="field" for="classification">Year/Classification: </label></td>
            <td><select name="classification" id="classification" required>
                <option value="" disabled selected>Select your year</option>
                <option value="superSenior">Super Senior</option>
                <option value="senior">Senior</option>
                <option value="junior">Junior</option>
                <option value="sophomore">Sophomore</option>
                <option value="freshman">Freshman</option>
                <option value="other">Other</option>
            </select></td>
        </tr>

        <tr>
            <td><label class="field" for="major">Major/School: </label></td>
            <td><select name="major" id="major" required>
                <option value="" disabled selected>Select your major</option>
                <option value="engineering">Engineering</option>
                <option value="business">Business</option>
                <option value="education">Education</option>
                <option value="fineArts">Fine Arts</option>
                <option value="liberalArts">Liberal Arts</option>
                <option value="naturalSciences">Natural Sciences</option>
                <option value="pharmacy">Pharmacy</option>
                <option value="geosciences">GeoSciences</option>
                <option value="publicAffairs">Public Affairs</option>
                <option value="architecture">Architecture</option>
                <option value="information">Information</option>
                <option value="law">Law</option>
                <option value="nursing">Nursing</option>
                <option value="socialWork">Social Work</option>
                <option value="communications">Communication</option>
                <option value="undeclared">Undeclared</option>
                <option value="other">Other</option>
            </select></td>
        </tr>

        <tr>
            <td><label class="field" for="gender">Gender: </label></td>
            <td><select name="gender" id="gender" required>\
                <option value="" disabled selected>Select your gender</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select></td>
        </tr>

        <tr>
            <td><label class="field" for="personality">Personality: </label></td>
            <td><select name="personality" id="personality" required>\
                <option value="" disabled selected>Select your personality</option>
                <option value="extroverted">Extroverted</option>
                <option value="introverted">Introverted</option>
            </select></td>
        </tr>

        <tr>
            <td><label class="field" for="recurrence">Recurrence: </label></td>
            <td><select name="recurrence" id="recurrence" required>\
                <option value="" disabled selected>Select your recurrence</option>
                <option value="recurring">Recurring</option>
                <option value="nextWeek">Next Week</option>
            </select></td>
        </tr>

    </table>

    <div style="text-align: center" id="submitButton">
        <button type="submit" class="btn btn-primary" id="formSubmitButton" align="center">Submit</button>
    </div>

</form>
</div>
</body>
</html>
