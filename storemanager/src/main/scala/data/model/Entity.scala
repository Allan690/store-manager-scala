package data.model

import java.sql.Timestamp
import java.util.UUID

trait Entity {
  def id: UUID
  def created: Timestamp
  def updated: Option[Timestamp]
  def deleted: Option[Timestamp]
}

case class User( id: UUID,
                 email: String,
                 password: String,
                 firstName: String,
                 lastName: String,
                 created: Timestamp,
                 updated: Option[Timestamp],
                 deleted: Option[Timestamp]
               )
extends Entity

case class Role ( id: UUID,
                  name: String,
                  created: Timestamp,
                  updated: Option[Timestamp],
                  deleted: Option[Timestamp]
                )
extends Entity

case class UserRole (user: User, role: Role)

case class Product (id: UUID,
                    name: String,
                    price: Float,
                    quantity: Int,
                    created: Timestamp,
                    updated: Option[Timestamp],
                    deleted: Option[Timestamp]
                   )
extends Entity

case class Sales (id: UUID,
                  revenue: Float,
                  created: Timestamp,
                  updated: Option[Timestamp],
                  deleted: Option[Timestamp]
                 )
extends Entity

case class ProductSalesUser (product: Product, sales: Sales, user: User)
