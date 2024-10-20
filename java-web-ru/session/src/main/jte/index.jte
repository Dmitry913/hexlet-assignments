@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(
content = @`
    @if(page == null || page.getName() == null)
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @else
        <p>Привет, ${page.getName()}</p>
        <a href="${NamedRoutes.logoutPath()}">Выйти</a>
    @endif

`
)