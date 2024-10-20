@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage

@param PostsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Все публикации</h1>
            <table class="table table-striped">
                @for(var post : page.getPosts())
                    <tr>
                        <td>
                            <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                        </td>
                    </tr>
                @endfor
            </table>
        </main>
        <p><a href="${NamedRoutes.postsPath()}/?page=${page.getCurrentPage()-1}">назад</a>       <a href="${NamedRoutes.postsPath()}/?page=${page.getCurrentPage()+1}">вперёд</a></p>
    </div>
`
)
