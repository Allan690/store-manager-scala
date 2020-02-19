package data.persistence
import data.model.User

import scala.concurrent.{ExecutionContext, Future}
import scala.language.{ existentials, postfixOps}


trait UserComponent extends RepoDefinition { this: DB =>
  import driver.api._

  class UserTable(tag: Tag) extends BaseTable[User](tag, "users") {
    val email = column[String]("email")
    val password = column[String]("password")
    val firstName = column[String]("first_name")
    val lastName = column[String]("last_name")

    def * =
      (id, email, password, firstName, lastName, created, updated.?, deleted.?) <> (User.tupled, User.unapply)
  }

  class UserRepository(implicit ec: ExecutionContext) extends BaseRepo[User, UserTable] {
    override val table = TableQuery[UserTable]

    private val fullName: UserTable => slick.lifted.Rep[String] = userTable =>
      userTable.firstName ++ userTable.lastName

    def allNames: Future[Seq[String]] = db.run {
      table map fullName result
    }
  }

}
