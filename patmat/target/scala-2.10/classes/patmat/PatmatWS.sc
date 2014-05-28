package patmat

import Huffman._
object PatmatWS {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  createCodeTree(List('a', 'b', 'a', 'c', 'c', 'd'))
                                                  //> res0: patmat.Huffman.CodeTree = Fork(Fork(Fork(Leaf(b,1),Leaf(d,1),List(b, d
                                                  //| ),2),Leaf(a,2),List(b, d, a),4),Leaf(c,2),List(b, d, a, c),6)

  decode(frenchCode, secret)                      //> res1: List[Char] = List(h, u, f, f, m, a, n, e, s, t, c, o, o, l)

}
  