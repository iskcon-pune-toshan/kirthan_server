/*
 * package org.iskon.controllers;
 * 
 * import java.util.List;
 * 
 * import org.iskon.models.TeamUser; import org.iskon.models.TeamUserSearch;
 * import org.iskon.models.UserTemple; import org.iskon.models.UserTempleSearch;
 * import org.iskon.services.UserTempleService; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/usertemple") public class UserTempleController {
 * 
 * @Autowired private UserTempleService userTempleService;
 * 
 * @RequestMapping(value = "/addusertemple", method = RequestMethod.PUT) public
 * List<UserTemple> addUserTemple(@RequestBody List<UserTemple> listUserTemple)
 * { List<UserTemple> res = userTempleService.addUserTemple(listUserTemple);
 * 
 * return res; }
 * 
 * 
 * 
 * @RequestMapping(value = "/getusertemple", method = RequestMethod.PUT) public
 * List<UserTemple> getUserTemple(@RequestBody UserTempleSearch
 * usertempleSearch) { List<UserTemple> req =
 * userTempleService.getUserTemple(usertempleSearch); return req; }
 * 
 * @RequestMapping(value = "/deleteusertemple", method = RequestMethod.PUT)
 * public List<UserTemple> deleteUserTemple(@RequestBody List<UserTemple>
 * listTUserTemple) { userTempleService.deleteUserTemple(listTUserTemple);
 * 
 * return listTUserTemple;
 * 
 * @RequestMapping(value = "/getusertemplewithdescription", method =
 * RequestMethod.PUT) public List<UserTemple> getUserTempleWithDescription() {
 * //System.out.println("queryParams: "+queryParams); List<UserTemple>
 * req=userTempleMappingService.getUserTempleWithDescription(); return req; }
 * 
 * }
 */