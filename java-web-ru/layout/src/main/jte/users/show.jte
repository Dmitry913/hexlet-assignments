@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <h1>${page.getUser().getFirstName()}</h1>
    <h1>${page.getUser().getLastName()}</h1>
    <h1>${page.getUser().getEmail()}</h1>
`
)
