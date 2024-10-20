@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

@template.layout.page(
content = @`
    @if(page.getError() != null)
        <ul>
            <li>${page.getError()}</li>
        </ul>
    @endif
    <form action="${NamedRoutes.loginPath()}" method="post">
        <div class="mb-3">
            <label class="form-label">
                логин
                <input type="text" class="form-control" name="name">${page.getName()}
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                пароль
                <input type="password" class="form-control" name="password"/></label>
        </div>
        <input type="submit" class="btn btn-primary" value="Сохранить"/>
    </form>

`
)