package Group_15.Trello_Project.board.service;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.repository.BoardRepository;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserServiceImplementation userService;
    public BoardModel createBoard(BoardModel boardModel, Integer user_id)
    {

        BoardModel board = boardRepository.save(boardModel);

        boolean success = userService.addBoardToUser(user_id, board);

        if(success) {
            return board;
        }

        return null;
    }

    public BoardModel addUserToBoard(Integer board_id, Integer user_id)
    {

        Optional<BoardModel> optionalBoardModel = boardRepository.findById(board_id);
        BoardModel updatedBoardModel =  null;
        boolean success = false;

        if(optionalBoardModel.isPresent())
        {
            updatedBoardModel = optionalBoardModel.get();
            success = userService.addBoardToUser(user_id, updatedBoardModel);
        }

        if(success) {
            return updatedBoardModel;
        }

        return null;

    }


    public BoardModel findBoardById(Integer board_id)
    {
        BoardModel boardModel = null;

        Optional<BoardModel> optionalBoardModel = boardRepository.findById(board_id);

        if(optionalBoardModel.isPresent())
        {
            boardModel = optionalBoardModel.get();
        }

        return boardModel;
    }

    public void deleteBoard(@PathVariable Integer board_id)
    {
        boardRepository.deleteById(board_id);
    }

    public List<BoardModel> getAllBoards()
    {
        return boardRepository.findAll();
    }


    public BoardModel getBoard(@PathVariable Integer board_id)
    {
        BoardModel boardModel = null;
        Optional<BoardModel> board = null;

        Optional<BoardModel> optionalBoardModel = boardRepository.findById(board_id);

        if(optionalBoardModel.isPresent()) {

            boardModel = optionalBoardModel.get();
        }


        return boardModel;

    }

    public boolean removeUserFromBoard(@PathVariable Integer board_id, @RequestParam Integer user_id)
    {
        Optional<BoardModel> board = null;

        boolean success = false;

        board = boardRepository.findById(board_id);

        if(board.isPresent())
        {
            BoardModel boardModel = board.get();

            success = userService.deleteUserBoard(user_id, boardModel);

        }

        return success;
    }

}
