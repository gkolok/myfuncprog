package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a', 'b', 'd'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)))
  }

  test("decode with t1") {
    new TestTrees {
      val expected1 = "abb"
      val code1 = List(0, 1, 1)
      assert(decode(t1, code1) === expected1.toList)
    }
  }

  test("decode with t2") {
    new TestTrees {
      val t2code = List(0, 0, 0, 1, 1)
      assert(decode(t2, t2code) === "abd".toList)
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("code bits") {
    val codeTable = List(('a', List(0)), ('b', List(0, 1)), ('c', List(1)))
    def coder = codeBits(codeTable)_
    assert(coder('a') === List(0))
    assert(coder('b') === List(0, 1))
    assert(coder('c') === List(1))
  }

  test("convert t1") {
    new TestTrees {
      val expectedCodeTable = List(('a', List(0)), ('b', List(1)))
      assert(convert(t1) === expectedCodeTable)
    }
  }

  test("convert t2") {
    new TestTrees {
      val expectedCodeTable = List(('a', List(0,0)), ('b', List(0,1)), ('d', List(1)))
      assert(convert(t2) === expectedCodeTable)
    }
  }

  test("quickEncode with t1") {
    new TestTrees {
      val expected = List(1, 0)
      assert(quickEncode(t1)("ba".toList) === expected)

      val expected2 = List(0, 1, 1, 0)
      assert(quickEncode(t1)("abba".toList) === expected2)
    }
  }

  test("decode and quickEncode should be identity") {
    new TestTrees {
      val text = "abbbadaaddb"
      assert(decode(t2, quickEncode(t2)(text.toList)) === text.toList)
    }
  }

  test("createCodeTree") {
    new TestTrees {
      val text = "aabbb"
      assert(createCodeTree(text.toList) === t1)
      val text2 = "abbab"
      assert(createCodeTree(text2.toList) === t1)
      val text3 = "dabdbabdd"
      assert(createCodeTree(text3.toList) === t2)
      assert(encode(t2)("dba".toList).size === 5)
    }
  }

  test("createCodeTree from sample on WP") {
    val text = "this is an example of a huffman tree"
    val tree = createCodeTree(text.toList)
    assert(decode(tree, quickEncode(tree)(text.toList)) === text.toList)
  }
}
