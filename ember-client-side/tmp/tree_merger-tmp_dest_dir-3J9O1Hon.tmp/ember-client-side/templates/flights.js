import Ember from 'ember';
export default Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  


  data.buffer.push("<body>\n    <nav class=\"col-md-3\" id=\"main-form\">\n        <form action=\"\" id=\"search-form\">\n            <label for=\"one-way\">One Way</label>\n            <input type=\"checkbox\" id=\"one-way\"/>\n            <label for=\"round-trip\">Round trip</label>\n            <input type=\"checkbox\" id=\"round-trip\"/>\n            <hr/>\n            <label for=\"\">From:</label>\n            <input type=\"text\" class=\"form-control\" placeholder=\"Departure city\"/>\n            <label for=\"\">To:</label>\n            <input type=\"text\" class=\"form-control\" placeholder=\"Arrival city\"/>\n            <div class=\"row\">\n                <div class=\"col-md-6\">\n                    <label for=\"departure-date\">Departure:</label>\n                    <input type=\"date\" class=\"form-control\" id=\"departure-date\"/>\n                </div>\n                <div class=\"col-md-6\">\n                    <label for=\"returning-date\">Return:</label>\n                    <input type=\"date\" class=\"form-control\" id=\"returning-date\"/>\n                </div>\n            </div>\n            <input type=\"submit\" value=\"Search\" class=\"btn\" id=\"search-btn\"/>\n        </form>\n     </nav>\n    <div id=\"main-container\" class=\"col-md-8 col-md-offset-1\">\n\n    </div>\n</body>");
  
});
