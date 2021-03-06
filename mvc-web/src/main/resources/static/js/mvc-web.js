$(document).ready(function() {

	$("#jquery").click(function(event) {
		alert("Call JQuery codes");
	});

	$("#btnUserSearch").click(function(event) {
		console.log("Searching All Users from DB...");
		$.ajax({
			type : 'POST',
			url : 'http://localhost:9000/user/search',
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
		var userSearchForm = {};
		userSearchForm["username"] = $("#userSearchForm input[id=username]").val();
		userSearchForm["email"] = $("#userSearchForm input[id=email]").val();
		var checked = [];
        $("input[name='roles[]']:checked").each(function ()
        {
            checked.push(parseInt($(this).val()));
        });
		userSearchForm["roles"] = checked;
		userSearchForm["age"] = $("#userSearchForm input[id=age]").val();
		console.log("userSearchForm=" + userSearchForm);
		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			type : 'POST',
			url : 'http://localhost:9000/user/elasticsearch/searchUsers',
			data: JSON.stringify(userSearchForm),
			dataType : 'json',
			success : function(data) {
				$("#titleUserList").html("Users List from Elastic Search");
				buildUserListTable(data.content);
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
	
	$("#btnBulkInsert").click(function (event) {
		if (!confirm("Are you sure to Bulk insert 1.5M users to DB?")) {
			return;
		}
		console.log("Bulk insert data to DB...");
		$.ajax({
			type : 'POST',
			url : 'http://localhost:9000/user/bulkInsert',
			dataType : 'json',
			success : function(data) {
				alert(data.message);
			},
			error: function (xhr, error) {
				console.log(error);
				alert("Bulk insert data to DB error! Please see error log.");
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
		bodyHtml += "<td>" + item.rolesStr + "</td>";
		bodyHtml += "<td>" + item.age + "</td>";
		bodyHtml += "</tr>";
    });
	console.log(bodyHtml);
	$("#tbUserListBody").html(bodyHtml);
}

function buildPaginationBody(pageable) {
	var paginationBodyHtml = '';
	$("#paginationBody").html(paginationBodyHtml);
}

function confirmDeletion() {
	if (confirm("Are you sure to delete this?")) {
		return true;
	}
	return false;
}
