package dev.whitespace.service.bundler.visitor

import dev.whitespace.domain.recording.HtmlNode

object HtmlVisitor {
    fun visit(node: HtmlNode, fn: VisitorRawFn) {

    }

    private fun fromSourceableNode(node: HtmlNode, fn: VisitorRawFn) {

    }
}

//func fromSourceableNode(node *recording.HTMLNode, visit VisitRawFn) {
//    if hasTagName(node, "img") || hasTagName(node, "source") {
//        src := getAttribute(node, "src")
//        if src != nil && isExternalRef(src.Value) {
//            if next := visit(src.Value, "image"); next != nil {
//            src.Value = *next
//        }
//        }
//
//        srcset := getAttribute(node, "srcset")
//        if srcset != nil {
//            srcsetValue := ss.Parse(srcset.Value)
//            changed := false
//            for i := range srcsetValue {
//            value := &srcsetValue[i]
//            if next := visit(value.URL, "image"); next != nil {
//            value.URL = *next
//                    changed = true
//        }
//
//        }
//            if changed {
//                srcset.Value = srcsetValue.String()
//            }
//        }
//    }
//}
