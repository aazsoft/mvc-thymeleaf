$(document).ready(function() {

	$("#jquery").click(function(event) {
		alert("Call JQuery codes");
	});

	$("#btnUserSearch").click(function(event) {
		console.log("Searching All Users from DB...");
		$.ajax({
			type : 'POST',
			url : 'http://localhost:9000/user/search',
			//data : userSearchForm,
			dataType : 'json',
			success : function(data) {
				$("#titleUserList").html("Users List from DB");
				buildUserListTable(data);
			},
			error: function(xhr, error) {
				console.log(error);
				alert("Searching All users from DB error! Please see error log.");
			}
		});
		event.preventDefault();
	});

	$("#btnUserIndex").click(function (event) {
		console.log("Indexing all users to ElasticSearch cluster...");
		$.ajax({
			type : 'POST',
			url : 'http://localhost:9000/user/elasticsearch/index',
			dataType : 'json',
			success : function(data) {
				alert(data.message);
			},
			error: function(xhr, error) {
				console.log(error);
				alert("Indexing error! Please see error log.");
			}
		});
		event.preventDefault();
	});
	
	$("#btnUserElasticSearch").click(function (event) {
		console.log("Searching Users by Email using Elastic Search...");
		var email = $("#userSearchForm input[id=email]").val();
		console.log("email=" + email);
		$.ajax({
			type : 'POST',
			url : 'http://localhost:9000/user/elasticsearch/searchByEmail/' + email,
			dataType : 'json',
			success : function(data) {
				$("#titleUserList").html("Users List from Elastic Search");
				buildUserListTable(data);
			},
			error: function (xhr, error) {
				console.log(error);
				alert("Searching Users by Email using Elastic Search error! Please see error log.");
			}
		});
		event.preventDefault();
	});
	
	$("#btnElasticSearchClearIndices").click(function (event) {
		console.log("Clearing All Indices on Elastic Search...");
		$.ajax({
			type : 'POST',
			url : 'http://localhost:9000/user/elasticsearch/clearIndices',
			dataType : 'json',
			success : function(data) {
				alert(data.message);
			},
			error: function (xhr, error) {
				console.log(error);
				alert("Clearing All Indices on Elastic Search error! Please see error log.");
			}
		});
		event.preventDefault();
	});
	
});

function buildUserListTable(data) {
	var bodyHtml = '';
	$.each(data, function(index, item) {
		bodyHtml += "<tr>";
		bodyHtml += "<td>" + item.id + "</td>";
		bodyHtml += "<td>" + item.username + "</td>";
		bodyHtml += "<td><a href='" + "/user/" + item.id + "'>" + item.email + "</a></td>";
		bodyHtml += "<td>" + item.role + "</td>";
		bodyHtml += "<td>" + item.age + "</td>";
		bodyHtml += "</tr>";
    });
	console.log(bodyHtml);
	$("#tbUserListBody").html(bodyHtml);
}
