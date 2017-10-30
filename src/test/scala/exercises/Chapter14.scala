package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter14 extends FreeSpec with Matchers {

  "Pattern Matching and Case Classes" - {

    "Exercise 1" - {
      """--------
        |Your Java Development Kit distribution has the source code for much of the JDK
        |in the src.zip file. Unzip and search for case labels (regular expression case [^:]+:).
        |Then look for comments starting with // and containing [Ff]alls? thr to catch comments
        |such as // Falls through or // just fall thru. Assuming the JDK programmers follow the
        |Java code convention, which requires such a comment, what percentage of cases falls
        |through?
      """.stripMargin ignore {

      }
    }

    "Exercise 2" - {
      """--------
        |Using pattern matching, write a function swap that receives a pair of integers
        |and returns the pair with the components swapped.
      """.stripMargin in {

        val swapPair = (pair: (Int, Int)) => {
          pair match {
            case (first, second) => (second, first)
          }
        }

        swapPair((1, 2)) should be((2, 1))
      }
    }

    "Exercise 3" - {
      """--------
        |Using pattern matching, write a function swap that swaps the first two elements
        |of an array provided its length is at least two.
      """.stripMargin in {

        val swapPair = (array: Array[Int]) => {
          array match {
            case Array(first, second, rest@_*) => Array(second, first) ++ rest
            case Array(first, second) => Array(second, first)
            case a: Array[Int] => a
          }
        }

        swapPair(Array(1, 2, 3)) should be(Array(2, 1, 3))
        swapPair(Array(1, 2)) should be(Array(2, 1))
        swapPair(Array(1)) should be(Array(1))
      }
    }

    "Exercise 4" - {
      """--------
        |Add a case class Multiple that is a subclass of the Item class. For example,
        |Multiple(10, Article("Blackwell Toaster", 29.95)) describes ten toasters.
        |Of course, you should be able to handle any items, such as bundles or multiples,
        |in the second argument. Extend the price function to handle this new case.
      """.stripMargin in {

        abstract class Item
        case class Article(description: String, price: Double) extends Item
        case class Bundle(description: String, discount: Double, items: Item*) extends Item
        case class Multiple(quantity: Int, item: Item) extends Item

        def price(it: Item): Double = it match {
          case Article(_, p) => p
          case Bundle(_, disc, its@_*) => its.map(price).sum - disc
          case Multiple(quantity, item) => price(item) * quantity
        }

        val multipleItems = Multiple(10, Article("Blackwell Toaster", 29.95))
        price(multipleItems) should be(299.5)
      }
    }

    "Exercise 5" - {
      """--------
        |One can use lists to model trees that store values only in the leaves.
        |For example, the list ((3 8) 2 (5)) describes the tree
        |
        |      •
        |    / | \
        |   •  2  •
        |  / \    |
        |  3 8    5
        |
        |However, some of the list elements are numbers and others are lists.
        |In Scala, you cannot have heterogeneous lists, so you have to use a List[Any].
        |Write a leafSum function to compute the sum of all elements in the leaves,
        |using pattern matching to differentiate between numbers and lists.
      """.stripMargin in {

        def leafSum(tree: List[Any]): Int = {
          val partials = tree.collect {
            case List(leafs@_*) => leafs.asInstanceOf[List[Int]].sum
            case value: Int => value
          }
          partials.sum
        }

        val tree = List(List(3, 8), 2, List(5))
        leafSum(tree) should be(18)
      }
    }

    "Exercise 6" - {
      """--------
        |A better way of modeling such trees is with case classes. Let’s start with binary trees.
        |
        |  sealed abstract class BinaryTree
        |  case class Leaf(value: Int) extends BinaryTree
        |  case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree
        |
        |Write a function to compute the sum of all elements in the leaves.
      """.stripMargin in {

        sealed trait BinaryTree
        case class Leaf(value: Int) extends BinaryTree
        case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree

        def leafSum(tree: BinaryTree): Int = {
          tree match {
            case Node(left, right) => leafSum(left) + leafSum(right)
            case Leaf(x) => x
          }
        }

        val tree = Node(Node(Leaf(3), Leaf(8)), Node(Leaf(2), Leaf(5)))
        leafSum(tree) should be(18)
      }
    }

    "Exercise 7" - {
      """--------
        |Extend the tree in the preceding exercise so that each node can have an arbitrary
        |number of children, and reimplement the leafSum function. The tree in Exercise 5
        |should be expressible as:
        |
        |  Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))
      """.stripMargin in {

        sealed trait Tree
        case class Leaf(value: Int) extends Tree
        case class Node(leafs: Tree*) extends Tree

        def leafSum(tree: Tree): Int = {
          tree match {
            case Node(elements@_*) => elements.map(leafSum).sum
            case Leaf(x) => x
          }
        }

        val tree = Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))
        leafSum(tree) should be(18)
      }
    }

    "Exercise 8" - {
      """--------
        |Extend the tree in the preceding exercise so that each non leaf node stores an
        |operator in addition to the child nodes. Then write a function eval that computes
        |the value. For example, the tree
        |
        |      +
        |    / | \
        |   *  2  -
        |  / \    |
        |  3 8    5
        |
        |has value (3 * 8) + 2 + (–5) = 21.
        |Pay attention to the unary minus.
      """.stripMargin in {

        sealed trait Tree
        case class Leaf(value: Int) extends Tree
        case class Node(operator: Char, leafs: Tree*) extends Tree

        def eval(tree: Tree): Int = {
          tree match {
            case Node(op, leafs@_*) => op match {
              case '+' => leafs.map(eval).sum
              case '-' => -leafs.map(eval).sum
              case '*' => leafs.map(eval).product
            }
            case Leaf(x) => x
          }
        }

        val tree = Node('+', Node('*', Leaf(3), Leaf(8)), Leaf(2), Node('-', Leaf(5)))
        eval(tree) should be(21)
      }
    }

    "Exercise 9" - {
      """--------
        |Write a function that computes the sum of the non-None values in a List[Option[Int]].
        |Don’t use a match statement.
      """.stripMargin in {

        val sumPresent = (list: List[Option[Int]]) => {
          list.collect({ case Some(x) => x }).sum
        }

        val list = List(Some(1), None, Some(3), None, None, Some(-2), None, Some(7))
        sumPresent(list) should be(9)
      }
    }

    "Exercise 10" - {
      """---------
        |Write a function that composes two functions of type Double => Option[Double],
        |yielding another function of the same type. The composition should yield None
        |if either function does. For example,
        |
        |  def f(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
        |  def g(x: Double) = if (x >= 0) Some(sqrt(x)) else None
        |  val h = compose(g, f) // h(x) should be g(f(x))
        |
        |Then h(2) is Some(1), and h(1) and h(0) are None.
      """.stripMargin in {

        type T = Double => Option[Double]

        def compose(f: T, g: T): T = {
          (x: Double) =>
            g(x) match {
              case Some(n) => f(n)
              case None => None
            }
        }

        import math.sqrt

        def f(x: Double): Option[Double] = if (x != 1) Some(1 / (x - 1)) else None

        def g(x: Double): Option[Double] = if (x >= 0) Some(sqrt(x)) else None

        val h = compose(g, f)

        h(0) should be(None)
        h(1) should be(None)
        h(2) should be(Some(1))
      }
    }
  }
}
