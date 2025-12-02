package dev.whitespace.domain.recording

import kotlinx.serialization.Serializable

@Serializable
data class UserInput(
    val action: String,
    val target: String,
    val arguments: List<String>,
    @Deprecated("not in use") val targetDisplayName: String,
    val targetNode: Unit,
)


/*
type UserInput struct {
    Action    string   `json:"action"`
    Target    string   `json:"target"`
    Arguments []string `json:"arguments"`
    // deprecated
    TargetDisplayName string    `json:"targetDisplayName"`
    TargetNode        *HTMLNode `json:"targetNode"`
}*/
