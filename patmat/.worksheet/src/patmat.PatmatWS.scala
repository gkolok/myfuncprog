package patmat

import Huffman._
object PatmatWS {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(95); 
  println("Welcome to the Scala worksheet");$skip(55); val res$0 = 

  createCodeTree(List('a', 'b', 'a', 'c', 'c', 'd'));System.out.println("""res0: patmat.Huffman.CodeTree = """ + $show(res$0));$skip(31); val res$1 = 

  decode(frenchCode, secret);System.out.println("""res1: List[Char] = """ + $show(res$1))}

}
  