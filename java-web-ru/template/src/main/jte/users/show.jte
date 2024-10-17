@import exercise.dto.users.UserPage
@param UserPage user

<html>
<head>
    <title>Пользак</title>
</head>
<body>
<h1>Информация о пользователе</h1>
<div>
    <p>Имя: ${user.getUser().getFirstName()}</p>
    <p>Фамилия: ${user.getUser().getLastName()}</p>
    <p>Почта: ${user.getUser().getEmail()}</p>
</div>
</body>
</html>