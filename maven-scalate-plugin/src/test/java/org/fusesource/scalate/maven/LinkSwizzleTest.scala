package org.fusesource.scalate.maven

import org.fusesource.scalate.test.FunSuiteSupport

class LinkSwizzleTest extends FunSuiteSupport {

  val transformer = new SiteGenMojo

  // valid replacements
  testReplaces(
    """hello <a href='building'>Building</a> there!""",
    """hello <a href='building.html'>Building</a> there!""")

  testReplaces(
    """hello <a href="building">Building</a> there!""",
    """hello <a href="building.html">Building</a> there!""")

  testReplaces(
    """hello <a href="building">Building</a> something <a href="source">Source</a> there!""",
    """hello <a href="building.html">Building</a> something <a href="source.html">Source</a> there!""")

  // upper case versions
  testReplaces(
    """hello <A href='building'>Building</A> there!""",
    """hello <A href='building.html'>Building</A> there!""")

  testReplaces(
    """hello <A href="building">Building</A> there!""",
    """hello <A href="building.html">Building</A> there!""")

  testReplaces(
    """hello <A href="building">Building</A> something <A href="source">Source</A> there!""",
    """hello <A href="building.html">Building</A> something <A href="source.html">Source</A> there!""")


  // should not replace these...
  testReplaces(
    """hello <a href="http://fusesource.com/">FuseSource</a> there!""",
    """hello <a href="http://fusesource.com/">FuseSource</a> there!""")
  testReplaces(
    """hello <link href="css/style.css.html" rel="stylesheet" type="text/css"/> there!""",
    """hello <link href="css/style.css.html" rel="stylesheet" type="text/css"/> there!""")
  testReplaces(
    """hello <script src="foo.js" type="text/javascript"></script> there!""",
    """hello <script src="foo.js" type="text/javascript"></script> there!""")

  protected def testReplaces(html: String, expected: String): Unit = {
    test("replaces: " + html) {

      val answer = transformer.transformHtml(html, "foo.html")

      info("converted " + html)
      info("into: " + answer)

      expect(expected){answer}
    }
  }
}