@import exercise.dto.users.UsersPage
@param UsersPage users
@param String term

@template.layout.page(
content = @`
    <form action="/users" method="get">
        <input type="search" required name="term" value="${term}">
        <input type="submit" value="Искать">
    </form>

    @if(!users.getUsers().isEmpty())
        @for(var user : users.getUsers())
            <div>
                <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
            </div>
        @endfor
    @endif
`
)