@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page
@template.layout.page(
page = page,
content = @`
    <h1>Посты</h1>
    <table class="table table-striped">
        @for(var user : page.getPosts())
            <tr>
                <td>
                    <a href="${NamedRoutes.postPath(user.getId())}">${user.getName()}</a>
                </td>
                <td>
                    ${user.getBody()}
                </td>
            </tr>
        @endfor
    </table>
`
)
