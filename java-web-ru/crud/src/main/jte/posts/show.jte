@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage

@param PostPage page

@template.layout.page(
content = @`
    <p>${page.getPost().getName()}</p>
    <p>${page.getPost().getBody()}</p>

    <p><a href="${NamedRoutes.postsPath()}">Все публикации</a></p>
`
)
