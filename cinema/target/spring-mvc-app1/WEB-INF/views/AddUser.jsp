<!DOCTYPE html>
<html>

<head>
    <title>
        How to refresh a page
        using jQuery?
    </title>

    <script src=
                    "https://code.jquery.com/jquery-3.3.1.min.js">
    </script>
</head>

<body>
<h1 style="color: green">
    GeeksforGeeks
</h1>

<b>
    How to refresh a page
    using jQuery?
</b>

<p>
    GeeksforGeeks is a computer science
    portal with a huge variety of well
    written and explained computer science
    and programming articles, quizzes
    and interview questions.
</p>

<button type="button">
    Button to Reload page
</button>

<script type="text/javascript">
    $(document).ready(function () {
        $("button").click(function () {
            location.reload(true);
            alert('Reloading Page');
        });
    });
</script>
</body>

</html>