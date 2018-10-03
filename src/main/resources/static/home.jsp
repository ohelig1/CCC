<html>
<head>
	<title>Bootstrap Case</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-theme.min.css">


    <script src="js/angular.js"></script>
    <script src="js/angular-ui-router.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/apps.js"></script>
	<script src="js/spin.js"></script>
	<script src="js/main.js" />


</head>
<body>
	<br>
	<div class="container">
	<div id="spinner"></div>
		<div class="row">
			<div class="col-xs-3">
				<ul class="nav nav-list bs-docs-sidenav affix">
					<li role="presentation" class="active"><a id="a-input-data" href="#input-data" data-toggle="tab">Input Data</a></li>
					<li role="presentation" ><a id="a-new-job" href="#new-job" data-toggle="tab">Start A New Job</a></li>
					<li role="presentation"><a id="a-job-queue" href="#job-queue" data-toggle="tab">Pending Jobs</a></li>
					<li role="presentation"><a id="a-job-history" href="#job-history" data-toggle="tab">Job History</a></li>
					<li role="presentation"><a id="a-help" href="#help" data-toggle="tab">Help</a></li>
				</ul>
			</div>
			<div class="col-xs-9">
				<div class="tab-content">
					<div class="tab-pane active" id="input-data" >
						<div class="page-header">
							<h1>Input Dataset</h1>
						</div>
						<h2>Upload a New Data File</h2>
						<BR/>
						<input type="file" id="file-chooser" />
						<BR/><BR/>
						 <label for="name">FileName:</label>
  						<input type="text" placeholder="Name for File" class="form-control" id="name" style="width: 50%">
						<BR/><BR/>
						<label for="row">Number of Rows:</label>
  						<input type="text" class="form-control" placeholder="Number of Rows of File" id="row" style="width: 25%">
						<BR/><BR/>
						<label for="column">Number of Column:</label>
  						<input type="text" class="form-control" placeholder="Number of Columns of File" id="column" style="width: 25%">
  						<BR/>
						<a id="upload-button" href="#" class="btn btn-success">Upload File</a>
						<div id="results"></div>
						<br/>
						<br>
						
					</div>
					<div class="tab-pane" id="new-job">
						<div class="page-header">
							<h1>Start A New Job</h1>
						</div>
						 <form action="/Falcon/newjob" method="post" class="form-signin">  
							SELECT INPUT DATA:<select id="select-input-file" name = "fileName" class="form-control" style="width: 50%">
							</select>
							<BR/>						
							ANALYSIS TYPE:<select id="select-analysis-type" name="analysisType" class="form-control" style="width: 30%">
								<option value="LR">Linear Regression</option>
							</select>
							<BR/>
							ESTIMATED TIME TO COMPLETE (Minutes):<input id="etc" type="text" name="etc" class="form-control" id="name" style="width: 30%">
							<BR/><BR/>
							<button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 25%">Submit Job</button>
							<!-- <input type="submit" value="Submit here"/>	 -->
							<!-- <a id="submit-job-button" class="btn btn-success">Submit Job</a> -->
						<!--  <a id="submit-job-button" href="#" class="btn btn-success">Submit Job</a> --> 	
					 	</form>		 
					</div>
					<div class="tab-pane" id="job-queue" >
						<div class="page-header">
							<h1>Pending Job</h1>
						</div>
						<div class="watable" id="job-queue-table"></div>
					</div>
					<div class="tab-pane" id="job-history">
						<div class="page-header">
							<h1>Job History</h1>
						</div>
						<div class="watable" id="job-history-table"></div>
					</div>
					<div class="tab-pane" id="help" >
						<div class="page-header">
							<h1>Help Reference</h1>
						</div>
						<h2>Application Manual</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
