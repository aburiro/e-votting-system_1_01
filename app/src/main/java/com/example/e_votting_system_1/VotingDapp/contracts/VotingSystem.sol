// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract VotingSystem {
    struct Candidate {
        uint id;
        string name;
        uint voteCount;
    }

    struct Voter {
        bool hasVoted;
        uint vote;
    }

    address public admin;
    mapping(uint => Candidate) public candidates;
    mapping(address => Voter) public voters;
    uint public candidatesCount;
    uint public votersCount;
    bool public electionEnded;

    event VoteCast(address indexed voter, uint candidateId);
    event ElectionEnded(uint timestamp);

    constructor() {
        admin = msg.sender;
    }

    modifier onlyAdmin() {
        require(msg.sender == admin, "Only admin can perform this action");
        _;
    }

    function addCandidate(string memory _name) public onlyAdmin {
        candidatesCount++;
        candidates[candidatesCount] = Candidate(candidatesCount, _name, 0);
    }

    function vote(uint _candidateId) public {
        require(!electionEnded, "Election has ended");
        require(!voters[msg.sender].hasVoted, "You have already voted");
        require(_candidateId > 0 && _candidateId <= candidatesCount, "Invalid candidate");

        voters[msg.sender].hasVoted = true;
        voters[msg.sender].vote = _candidateId;
        candidates[_candidateId].voteCount++;
        votersCount++;

        emit VoteCast(msg.sender, _candidateId);
    }

    function endElection() public onlyAdmin {
        electionEnded = true;
        emit ElectionEnded(block.timestamp);
    }

    function getCandidate(uint _candidateId) public view returns (uint, string memory, uint) {
        return (
            candidates[_candidateId].id,
            candidates[_candidateId].name,
            candidates[_candidateId].voteCount
        );
    }
}

