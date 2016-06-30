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
    <link rel="stylesheet" type="text/css" href="WeeklyForm.css">
    <title>Lions Lunch Form</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/scripts/jquery.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
    <script src = "/FormValidation.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js-webshim/minified/polyfiller.js"></script>
    <script>
        webshim.activeLang('en');
        webshims.polyfill('forms');
        webshims.cfg.no$Switch = true;
    </script>
</head>

<body>
    <h1>Welcome to Lions Lunches Form</h1>

    <table align="center">
        <tr>
            <td>
                <form action = "WeeklyForm" method = "post" id = "formID">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="field" for = "name">First and Last Name: </label>
                            <input type = "text" pattern="[a-zA-Z\s]+" name = "name" id = "name" placeholder = "Justin Kang" required><br>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="field" for = "eid">UT EID: </label>
                            <input type = "text" pattern="[a-zA-Z0-9]+" name = "eid" id = "eid" placeholder = "jk36542" maxlength="10" required><br>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="field" for = "phoneNumber">Phone Number:</label>
                            <input type = "tel" pattern="(?:\(\d{3}\)|\d{3})[- ]?\d{3}[- ]?\d{4}" name = "phoneNumber" id = "phoneNumber" placeholder = "4692366449" title="Please enter your phone number in the form 4692366449 with no spaces or symbols." required><br>
                            <%--<script>--%>
                                <%--document.getElementById("phoneNumber").setCustomValidity("Please enter your phone number in the form 4692366449 with no spaces or symbols.");--%>
                            <%--</script>--%>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="field" for="email">Email Address: </label>
                            <input type = "email" email = "email" id="email" placeholder="justin.kang@utexas.edu" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="field" for="classification">Year/Classification: </label>
                            <select name="classification" id="classification" required>
                                <option value="" disabled selected>Select your year</option>
                                <option value="superSenior">Super Senior</option>
                                <option value="senior">Senior</option>
                                <option value="junior">Junior</option>
                                <option value="sophomore">Sophomore</option>
                                <option value="freshman">Freshman</option>
                                <option value="other">Other</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="field" for="major">Major/School: </label>
                            <select name="major" id="major" required>
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
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="field" for="gender">Gender: </label>
                            <select name="gender" id="gender" required>\
                                <option value="" disabled selected>Select your gender</option>
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                                <option value="other">Other</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="field" for="personality">Personality: </label>
                            <select name="personality" id="personality" required>\
                                <option value="" disabled selected>Select your personality</option>
                                <option value="extroverted">Extroverted</option>
                                <option value="introverted">Introverted</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="field" for="recurrence">Recurrence: </label>
                            <select name="recurrence" id="recurrence" required>\
                                <option value="" disabled selected>Select your recurrence</option>
                                <option value="recurring">Recurring</option>
                                <option value="nextWeek">Next Week</option>
                            </select>
                        </div>
                    </div>
                    <div class="wrapper">
                        <input type="submit" value="Submit">
                    </div>
                </form>
            </td>
        </tr>
    </table>

</body>
</html>
