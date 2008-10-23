update study_participant set participant_did = Concat('XYZ',substr(participant_did, 7))
where participant_did like 'CG%';
commit;

