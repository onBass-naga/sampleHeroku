<%@ page import="com.area_b.samples.Todo" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
    <style type="text/css" media="screen">

    #page-body {
        margin: 2em 1em 1.25em 1em;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    </style>

</head>
<body>
<div id="page-body" role="main">
    <h1>CRUDサンプル ～Todoリスト～</h1>
    <p>Heroku で Postgres を使うためのサンプル</p>
    <script type="text/javascript">
        $(function() {
            $('.choiced').change(function() {
                var suffix = $(this).attr("id");
                $('#priority').val($('#priority' + suffix).text());
                $('#description').val($('#description' + suffix).text());
                $('#deadline').val($('#deadline' + suffix).text());
            });
        });
    </script>
    <div id="todo-list" role="navigation">
        <h2>TODOs</h2>
        <g:form controller="todo" method="post">
        <div>
            優先順位：<g:textField id="priority" name="priority" size="2"/>
            内容：<g:textField id="description" name="description"/>
            〆切：<g:textField id="deadline" name="deadline"/>
            <br />
            <g:actionSubmit value="add" action="create"/>
            <g:actionSubmit value="save" action="update"/>
            <g:actionSubmit value="done" action="delete"/>
        </div>
        <table>
            <tr>
                <th></th>
                <th>優先順位</th>
                <th>内容</th>
                <th>〆切</th>
            </tr>
          <g:each status="i" var="todo" in="${tasks.sort { it.priority } }">
            <tr>
                <td><g:radio class="choiced" id="_${i}" name="id" value="${todo.id}" /></td>
                <td id="priority_${i}">${todo.priority}</td>
                <td id="description_${i}">${todo.description}</td>
                <td id="deadline_${i}">${todo.deadline}</td>
            </tr>
          </g:each>
        </table>
        </g:form>
    </div>
</div>
</body>
</html>


