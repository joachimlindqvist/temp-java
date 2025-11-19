package dev.whitespace.domain.recording

import dev.whitespace.domain.recording.serializer.NullAsEmptyListSerializer
import kotlinx.serialization.Serializable

@Serializable
data class HtmlNode(
    val nodeType: Int,
    val tagName: String?,
    val nodeValue: String?,
    val nodeName: String?,
    val namespace: String?,
    val attributes: List<HtmlNodeAttribute>?,
    val properties: List<HtmlNodeProperty>?,
    val children: List<HtmlNode>?,
    val shadowRoot: HtmlNode?,
) {
    fun hasTagName(tagName: String): Boolean {
        return when (this.tagName) {
            is String -> this.tagName.equals(tagName, ignoreCase = true)
            null -> false
        }
    }

    fun getAttribute(name: String): HtmlNodeAttribute? {
        return attributes?.firstOrNull { it.name.equals(name, ignoreCase = true) }
    }

    fun getAttributeNs(name: String, ns: String): HtmlNodeAttribute? {
        return attributes?.firstOrNull {
            it.namespace != null &&
            it.namespace.equals(ns, ignoreCase = true) &&
            it.name.equals(name, ignoreCase = true)
        }
    }
}

//func getAttributeNS(node *recording.HTMLNode, name string, namespace string) *recording.HTMLNodeAttribute {
//    for i := range node.Attributes {
//        attr := &node.Attributes[i]
//        if attr.Namespace == nil {
//            continue
//        }
//
//        if strings.EqualFold(attr.Name, name) && strings.EqualFold(*attr.Namespace, namespace) {
//            return attr
//        }
//    }
//    return nil
//}


@Serializable
data class HtmlNodeAttribute(
    val name: String,
    val value: String,
    val namespace: String?,
)

@Serializable
data class HtmlNodeProperty(
    val name: String,
    val value: DomArgument,
)