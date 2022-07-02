package Group_15.Trello_Project.board.controller;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @PostMapping(path="/saveBoard/{user_id}", consumes = "application/json", produces = "application/json")
    public BoardModel createBoard(@RequestBody BoardModel boardModel, @PathVariable Integer user_id)
    {
        return boardService.createBoard(boardModel, user_id);
    }

    @PutMapping(path="/addUserToBoard/{board_id}")
    public BoardModel addUserToBoard(@PathVariable Integer board_id, @RequestParam Integer user_id)
    {

        return boardService.addUserToBoard(board_id, user_id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteBoard/{board_id}")
    public void deleteBoard(@PathVariable Integer board_id)
    {
        boardService.deleteBoard(board_id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllBoards")
    public List<BoardModel> getAllBoards()
    {
        return boardService.getAllBoards();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getBoard/{board_id}")
    public BoardModel getBoard(@PathVariable Integer board_id)
    {
        return boardService.getBoard(board_id);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/removeUserFromBoard/{board_id}")
    public boolean removeUserFromBoard(@PathVariable Integer board_id, @RequestParam Integer user_id)
    {
        return boardService.removeUserFromBoard(board_id, user_id);

    }
}
