package com.gitlab.vasilievpavel96.controllers

import com.gitlab.vasilievpavel96.extensions.get
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.stream.createHTML
import java.util.*
import javax.servlet.ServletConfig
import javax.servlet.annotation.*
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "Home", urlPatterns = ["/"],
        initParams = [(WebInitParam(name = "backgroundColor", value = "#64FFDA"))])
@ServletSecurity(value = HttpConstraint(rolesAllowed = arrayOf("admin","user")),
        httpMethodConstraints = arrayOf(HttpMethodConstraint(value = "GET",rolesAllowed = arrayOf("admin","user"))))
class HomeController : HttpServlet() {
    private lateinit var backgroundColor: String
    private lateinit var resources: ResourceBundle
    override fun init(config: ServletConfig) {
        super.init(config)
        backgroundColor = config.getInitParameter("backgroundColor")
    }

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        resources = ResourceBundle.getBundle("Blog", req.locale)
        val document = createHTML(true).body {
            attributes["style"] = "background-color:$backgroundColor"
            h1 {
                +resources["welcome"]
            }
        }
        with(res.writer) {
            print(document)
            close()
        }
    }
}

